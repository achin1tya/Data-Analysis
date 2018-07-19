from PIL import Image
import pytesseract
import argparse
import cv2
import os
import sys
import numpy as np
import pandas as pd
from wordcloud import WordCloud
from matplotlib import pyplot as plt


img = cv2.imread("1.png")
gray = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)             # getting the image and converting to gray

# thresholding 
gray = cv2.threshold(gray, 0, 255, cv2.THRESH_BINARY | cv2.THRESH_OTSU)[1]

#denoising
gray = cv2.medianBlur(gray, 3)

os.getpid()

filename = "{}.png".format(os.getpid())
cv2.imwrite(filename, gray)

Image.open(filename)

import pytesseract                     # getting the pytesseract to program
pytesseract.pytesseract.tesseract_cmd = 'C:/Program Files (x86)/Tesseract-OCR/tesseract'

text = pytesseract.image_to_string(Image.open(filename))
# os.remove(filename)
print(text)
#import nltk
#nltk.download()

from nltk.tokenize import sent_tokenize, word_tokenize
from nltk.corpus import stopwords
stops = set(stopwords.words('english'))

words=word_tokenize(text)
clean_wrods = [w for w in  words if not w in stops]

df=pd.DataFrame(clean_wrods)                #getting clean text i.e removing the stopwords
uniqueWords=df[0].unique()

#unique words generating dictionary
def makeDictionary(array,cleanWords):
    dict1={} 
    for word in array:
        count=0
        for i in range(len(cleanWords)):
            if word==cleanWords[i]:
                count=count+1
        dict1[word]=count
    return dict1

img=cv2.imread("5.jpg")              #doing above things for the second picture
# thresholding 
gray = cv2.threshold(gray, 0, 255, cv2.THRESH_BINARY | cv2.THRESH_OTSU)[1]

#denoising
gray = cv2.medianBlur(gray, 3)
os.getpid()
filename = "{}.jpg".format(os.getpid())
cv2.imwrite(filename, gray)
text2 = pytesseract.image_to_string(Image.open(filename))
# os.remove(filename)
print(text2)
wordsOfText2=word_tokenize(text2)
wordsOfText2
clean_text2=[w for w in wordsOfText2 if not  w  in stops]

def makedataFrame(array):
    return pd.DataFrame(array)   

text2dataFrame=makedataFrame(clean_text2)
dict2=makeDictionary(text2dataFrame[0].unique(),clean_text2)

dict1=makeDictionary(df[0].unique(),clean_wrods)
dict2=makeDictionary(text2dataFrame[0].unique(),clean_text2)   # generating the dictionary of words for second file
index2=dict2.keys()

#getting the words from the dictionary 
def getIndex(dictionary):                                    
    a=[]
    for k,_ in dictionary.items():
        a.append(k)
    return a

#comparing two dictionaries to get overall result 
def generateResult(dictonaryOfFirstFile,dictionaryOfAllPreviosPdf):   
    index1=getIndex(dictonaryOfFirstFile)
    index2=getIndex(dictionaryOfAllPreviosPdf)
    valSum=0
    for indexValue in index1:
        if indexValue in index2 :
            valSum=valSum+(max(dictonaryOfFirstFile[indexValue],dictionaryOfAllPreviosPdf[indexValue])-min(dictonaryOfFirstFile[indexValue],dictionaryOfAllPreviosPdf[indexValue]))/max(dictonaryOfFirstFile[indexValue],dictionaryOfAllPreviosPdf[indexValue])
        return valSum/max(len(dictonaryOfFirstFile),len(dictionaryOfAllPreviosPdf))*100

ans=generateResult(dict2,dict1)

words=' '.join(text2dataFrame[0])
# generating wordcloud 
wordcloud=WordCloud(background_color='white',height=2500,width=3000).generate(words)
from matplotlib import pyplot as plt
plt.imshow(wordcloud)

 # importing required modules
import PyPDF2

def getTextFromPdf(filenameOfPdf):
      # creating a pdf file object
    pdfFileObj = open(str(filenameOfPdf), 'rb')

      # creating a pdf reader object
    pdfReader = PyPDF2.PdfFileReader(pdfFileObj)

      # printing number of pages in pdf file
    print(pdfReader.numPages)

      # creating a page object
    pageObj = pdfReader.getPage(5)

      # extracting text from page
    text=pageObj.extractText()

      # closing the pdf file object
    pdfFileObj.close()
    return text

import sys
try:
    from StringIO import StringIO
except ImportError:
    from io import StringIO
import pandas as pd
testData=StringIO(text)
df=pd.read_csv(testData,sep="\n")
cleanWords=df["Food"].unique()
dictionary=makeDictionary(cleanWords,df["Food"])
words=" ".join(df["Food"])
wordcloud=WordCloud(background_color='white',height=2500,width=3000).generate(words)
plt.imshow(wordcloud)

# Doing for the second pdf
text2=getTextFromPdf("BuildingRealEstate.pdf")
testdata2=StringIO(text2)
dfData=pd.read_csv(testdata2,sep="\n")
words2=" ".join(dfData["Demand for affordabl"])
wordcloud2=WordCloud(background_color='white',height=2500,width=3000).generate(words2)
plt.imshow(wordcloud2)


cleanWords2=dfData["Demand for affordabl"].unique()
dictionary2=makeDictionary(cleanWords2,dfData["Demand for affordabl"])
generateResult(dictionary,dictionary2)