import mysql.connector
import serverconstant as serverconstant
from generateTF import *
def dbconnection():
    mydb = mysql.connector.connect(
      host=serverconstant.host,
      user=serverconstant.username,
      passwd=serverconstant.password,
      database=serverconstant.database
    )
    return mydb


def executeUpdate(sql,val):
    mydb=dbconnection()
    mycursor = mydb.cursor()
    mycursor.execute(sql, val)
    mydb.commit()
    print(mycursor.rowcount, "was inserted.") 

def executeQuery(sql):
    mydb=dbconnection()
    mycursor = mydb.cursor()
    mycursor.execute(sql)
    myresult = mycursor.fetchall()
    for x in myresult:
      print(x[0])

sql = "INSERT INTO wordtf (word, tfidf) VALUES (%s, %s)"
term = read_txt2dictionary("termFrequency_idf_small_file_30000.txt.txt")
for d in term:
    if d:
        for k in d:
            fre = d[k]
            val = (k, fre)
            executeUpdate(sql,val)

#sql="SELECT * FROM follower"
#executeQuery(sql)