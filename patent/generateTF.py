import csv
import operator
import sys
sys.path.append('C:\\Python35\\Lib\site-packages')
from nltk.corpus import stopwords
from nltk import word_tokenize
import re
import glob
from os.path import basename

BASE_DIR="F:\\work\\project\\patent\\"
def remove_specialsymbol(sentence):
    sentence = re.sub(r"[^a-zA-Z0-9]+", ' ', sentence)
    return sentence
  
def store_dictionary2txt(dictionaryData,txtFileName):
    file = open(txtFileName, "w")
    file.write(str(dictionaryData))
    file.close()

def read_txt2dictionary(txtFileName):    
    file = open(txtFileName, 'r')
    obj_code = 'dict({})'.format(file.read())
    result = eval(obj_code)
    return result
  
def remove_stopwords(sentence):
    stop_words = set(stopwords.words('english')) 
    word_tokens = word_tokenize(sentence) 
    filtered_sentence = [w for w in word_tokens if not w in stop_words] 
    filtered_sentence =""
    for w in word_tokens: 
        if w not in stop_words: 
            filtered_sentence+=w+" " 
    return filtered_sentence 
def generate_TFIDF():
    print("Generating TFIDF !")
    i=0
    f=float(0.01)
    globalTermFrequency= read_txt2dictionary(BASE_DIR+"globalTermFrequency.txt")
    term = read_txt2dictionary(BASE_DIR+"term.txt")
    print(globalTermFrequency)
    print("-----------------------------------------------------------")
    print(term)
    
    tfidf={}
    for team in term.keys():
        a=term[team]
        for word in a:
            tf=a[word]
            #idf=globalTermFrequency[word]
            tfidf[word]=tf
            print(word+" "+str(tfidf[word]))
    sorted_by_value = sorted(tfidf.items(), key=lambda kv: kv[1])
    return tfidf
def generate_TF(path):
    print("Searching .tsv Files in -- ",path, " -- Directory...!")
    i=0
    f=float(0.01)
    globalTermFrequency= read_txt2dictionary(BASE_DIR+"globalTermFrequency.txt")
    num_of_patents=1200
    termFrequency = {}
    files = glob.glob(path)
    ind=0
    print("Found ",len(files)," .tsv Files")
    for name in files:
        with open(name) as fd:
            rd = csv.reader(fd, delimiter="\t")
            print("Reading ",name," File")
            ind=ind+1
            if(ind>1):
                continue
            for row in rd:
                try:
                    terms_in_doc = {}
                    abstract = row[5]
                    abstract = remove_stopwords(abstract)
                    abstract = remove_specialsymbol(abstract)
                    wordTockenize = abstract.split()
                    for word in wordTockenize:
                        if word in terms_in_doc:
                            terms_in_doc[word] += 1
                        else:
                            terms_in_doc[word] = 1
                    """
                    for word in wordTockenize:
                        if word in globalTermFrequency:
                            globalTermFrequency[word] += 1
                        else:
                            globalTermFrequency[word] = 1    
                    """        
                    #sorted_x = sorted(terms_in_doc.items(), key=operator.itemgetter(1),reverse=True)
                    #

                    for word in list(terms_in_doc.keys()):
                        tf=terms_in_doc[word]
                        globalCnt=globalTermFrequency[word]
                        idf=float(tf)/float(globalCnt)
                        if(idf>f):
                            terms_in_doc[word]=idf
                        else:
                            terms_in_doc.pop(word, None)
                    termFrequency[i] = terms_in_doc
                    del(terms_in_doc)
                    i = i+1
                except:
                    pass
 
            #j2 = dict((k, v) for k, v in terms_in_doc.items() if v >= 0.01)
            #sorted_x = sorted(terms_in_doc.items(), key=operator.itemgetter(1),reverse=True)
            #j2 = [i for i in sorted_x if sorted_x[i] >=  float(0.01)]
            
            store_dictionary2txt(termFrequency,BASE_DIR+"termFrequency_idf_"+basename(name)+".txt")
            #del(j2)
            #del(termFrequency)
            #store_dictionary2txt(globalTermFrequency,"globalTermFrequency.txt")
            #del(termFrequency)
            #termFrequency={}
    
    
    return termFrequency
    

def generate_global_TF(path):
    print("Searching .tsv Files in -- ",path, " -- Directory...!")
    i=0
    f=float(0.01)
    #globalTermFrequency= read_txt2dictionary("globalTermFrequency.txt")
    globalTermFrequency={}
    num_of_patents=1200
    termFrequency = {}
    files = glob.glob(path)
    print("Found ",len(files)," .tsv Files")

    for name in files:
        with open(name) as fd:

            rd = csv.reader(fd, delimiter="\t")
            print("Reading ",name," File")
            
            for row in rd:
                try:
                    terms_in_doc = {}
                    abstract = row[5]
                    abstract = remove_stopwords(abstract)
                    abstract = remove_specialsymbol(abstract)
                    wordTockenize = abstract.split()
                    """
                    for word in wordTockenize:
                        if word in terms_in_doc:
                            terms_in_doc[word] += 1
                        else:
                            terms_in_doc[word] = 1
                    """
                    for word in wordTockenize:
                        if word in globalTermFrequency:
                            globalTermFrequency[word] += 1
                        else:
                            globalTermFrequency[word] = 1    
                    """        
                    #sorted_x = sorted(terms_in_doc.items(), key=operator.itemgetter(1),reverse=True)
                    #

                    for word in list(terms_in_doc.keys()):
                        tf=terms_in_doc[word]
                        globalCnt=globalTermFrequency[word]
                        idf=float(tf)/float(globalCnt)
                        if(idf>f):
                            terms_in_doc[word]=idf
                        else:
                            terms_in_doc.pop(word, None)
                    termFrequency[i] = terms_in_doc
                    del(terms_in_doc)
                    
                    i = i+1
                    """
                except:
                    pass
 
            #j2 = dict((k, v) for k, v in terms_in_doc.items() if v >= 0.01)
            #sorted_x = sorted(terms_in_doc.items(), key=operator.itemgetter(1),reverse=True)
            #j2 = [i for i in sorted_x if sorted_x[i] >=  float(0.01)]
            
            #store_dictionary2txt(termFrequency,"termFrequency_idf_"+basename(name)+".txt")
            #del(j2)
            #del(termFrequency)
            store_dictionary2txt(globalTermFrequency,BASE_DIR+globalTermFrequency+".txt")
            #del(termFrequency)
            #termFrequency={}
    
    
    return globalTermFrequency    

path = BASE_DIR+'dataset/*.txt'

#globalTermFrequency=generate_global_TF(path)
#del(globalTermFrequency)

#termFrequency = generate_TF(path)
#del(termFrequency)
#store_dictionary2txt(termFrequency,"term.txt")
#term = read_txt2dictionary("term.txt")
#print(term)


tfidf=generate_TFIDF()
store_dictionary2txt(tfidf,BASE_DIR+"tfidf.txt")
print(tfidf)