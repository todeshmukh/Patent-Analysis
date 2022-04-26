import csv
import operator
from nltk.corpus import stopwords
from nltk import word_tokenize
import re
import glob

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

def generate_TF(path):
    print("Searching .tsv Files in -- ",path, " -- Directory...!")
    i=0
    termFrequency = {}
    files = glob.glob(path)
    print("Found ",len(files)," .tsv Files")
    for name in files:
        with open(name) as fd:
            rd = csv.reader(fd, delimiter="\t")
            print("Reading ",name," File")
            for row in rd:
                abstract = row[5]
                terms_in_doc = {}
                abstract = remove_stopwords(abstract)
                abstract = remove_specialsymbol(abstract)
                wordTockenize = abstract.split()
                for word in wordTockenize:
                    if word in terms_in_doc:
                        terms_in_doc[word] += 1
                    else:
                        terms_in_doc[word] = 1
                sorted_x = sorted(terms_in_doc.items(), key=operator.itemgetter(1),reverse=True)
                termFrequency[i] = sorted_x
                i = i+1
    return termFrequency
#path = '*.tsv'
#termFrequency = generate_TF(path)
#store_dictionary2txt(termFrequency,"term.txt")
#term = read_txt2dictionary("term.txt")

#print(term)