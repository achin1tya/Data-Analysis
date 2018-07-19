import cv2
from PIL import Image
import pytesseract
import argparse
import cv2
import os
import sys
img = cv2.imread("1.png")
gray = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY
# thresholding 

gray =cv2.threshold(gray, 0, 255, cv2.THRESH_BINARY | cv2.THRESH_OTSU)[1]
#denoising
gray = cv2.medianBlur(gray, 3)
os.getpid()
filename = "{}.png".format(os.getpid())
cv2.imwrite(filename, gray)
Image.open(filename)
import pytesseract
pytesseract.pytesseract.tesseract_cmd = 'C:/Program Files (x86)/Tesseract-OCR/tesseract'
text = pytesseract.image_to_string(Image.open(filename))
# os.remove(filename)
print(text)
import nltk
from nltk.tokenize import sent_tokenize, word_tokenize
from nltk.corpus import stopwords
stops = set(stopwords.words('english'))
words=word_tokenize(text)
clean_wrods = [w for w in  words if not w in stops]
import numpy as np
newWords=np.asarray(clean_wrods)
import pandas as pd
df=pd.DataFrame(clean_wrods)
df[0].unique()
uniqueWords=df[0].unique()
dict1={} 
count=0
for word in uniqueWords:
    for i in range(len(clean_wrods)):
        if word==clean_wrods[i]:
            count=count+1
    dict1[word]=count
    
    