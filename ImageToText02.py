
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
import PyPDF2

#getting two dictionaries to compare 
def generateResult(dictonaryOfFirstFile,dictionaryOfAllPreviosPdf):   
    index1=getIndex(dictonaryOfFirstFile)
    index2=getIndex(dictionaryOfAllPreviosPdf)
    valSum=0
    for indexValue in index1:
        if indexValue in index2 :
            if dictonaryOfFirstFile[indexValue]==dictionaryOfAllPreviosPdf[indexValue]:
                valSum=valSum+1
            else:
                valSum=valSum+(min(dictonaryOfFirstFile[indexValue],dictionaryOfAllPreviosPdf[indexValue]))/max(dictonaryOfFirstFile[indexValue],dictionaryOfAllPreviosPdf[indexValue])        
    return (valSum/max(len(dictonaryOfFirstFile),len(dictionaryOfAllPreviosPdf)))*100


#getting the words from the dictionary 
def getIndex(dictionary):                                    
    a=[]
    for k,v in dictionary.items():
        a.append(k)
    return a



# unique words generating dictionary
def makeDictionary(array,cleanWords):
    dict1={} 
    for word in array:
        count=0
        for i in range(len(cleanWords)):
            if word==cleanWords[i]:
                count=count+1
        dict1[word]=count
    return dict1

def makeDataFrameFromText(textData):
    try:
        import StringIO 
    except ImportError:
        from io import StringIO
        
    testData=StringIO(textData)
    import pandas as pd
    dataFrameForPdf=pd.read_csv(testData,sep="\n")  # making the text to a dataFrame Column
    columnName=list(dataFrameForPdf.columns.values)
    return dataFrameForPdf,columnName

def makeDictionaryFromText(textData):
    dataFrameForPdf,columnName=makeDataFrameFromText(textData)
    uniqueWords=dataFrameForPdf[str(columnName[0])].unique()
    temp={}
    temp=makeDictionary(uniqueWords,dataFrameForPdf[str(columnName[0])])
    return temp



def getTextFromPDFALLPages(nameOfPdfFile):
    pdfFileObj = open(str(nameOfPdfFile), 'rb')

      # creating a pdf reader object
    pdfReader = PyPDF2.PdfFileReader(pdfFileObj)

      # printing number of pages in pdf file
    pages=pdfReader.numPages

    wholetext=""
    # creating a page object
    for i in range (pages):
        pageObj = pdfReader.getPage(i)

      # extracting text from page
        text=pageObj.extractText()
        wholetext=wholetext+text
      # closing the pdf file object
    pdfFileObj.close()
    return wholetext


def mainProgram():
    file1=input("enter name of first file")
    file2=input("enter name of second file")
    text1=getTextFromPDFALLPages(file1)
    text2=getTextFromPDFALLPages(file2)
    dictionary1=makeDictionaryFromText(text1)
    dictionary2=makeDictionaryFromText(text2)
    ans=generateResult(dictionary1,dictionary2)
    print(ans)

mainProgram()