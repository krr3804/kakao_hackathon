from tensorflow.keras.layers import Embedding, Dense, GRU
from tensorflow.keras.models import Sequential
from tensorflow.keras.callbacks import EarlyStopping, ModelCheckpoint
import re
import re
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import urllib.request
from collections import Counter
from konlpy.tag import Mecab
from tensorflow.keras.preprocessing.text import Tokenizer
from tensorflow.keras.preprocessing.sequence import pad_sequences
import os
from tensorflow.keras.models import model_from_json 
from keras.models import load_model
from konlpy.tag import Mecab
from sklearn.model_selection import train_test_split
max_len = 80
mecab = Mecab()
total_data = pd.read_table('ratings_total.txt', names=['ratings', 'reviews'])
total_data['label'] = np.select([total_data.ratings > 3], [1], default=0)
total_data[:5]
total_data['reviews'].nunique()
total_data.drop_duplicates(subset=['reviews'], inplace=True)
train_data, test_data = train_test_split(total_data, test_size = 0.25, random_state = 42)
stopwords = ['도', '는', '다', '의', '가', '이', '은', '한', '에', '하', '고', '을', '를', '인', '듯', '과', '와', '네', '들', '듯', '지', '임', '게']
train_data['tokenized'] = train_data['reviews'].apply(mecab.morphs)
train_data['tokenized'] = train_data['tokenized'].apply(lambda x: [item for item in x if item not in stopwords])
X_train = train_data['tokenized'].values
tokenizer = Tokenizer()
tokenizer.fit_on_texts(X_train)

stopwords = ['도', '는', '다', '의', '가', '이', '은', '한', '에', '하', '고', '을', '를', '인', '듯', '과', '와', '네', '들', '듯', '지', '임', '게']



# json_file = open("model2.json", "r")
# loaded_model_json = json_file.read() 
# json_file.close()
# loaded_model = model_from_json(loaded_model_json)
# loaded_model.summary()
# loaded_model.load_weights("model_weight.h5")
loaded_model = load_model('best_model.h5')

def sentiment_predict1(new_sentence):
  # print("1 :",new_sentence  )
  new_sentence = re.sub(r'[^ㄱ-ㅎㅏ-ㅣ가-힣 ]','', new_sentence)
  new_sentence = mecab.morphs(new_sentence)
  # print("2 :",new_sentence  )
  new_sentence = [word for word in new_sentence if not word in stopwords]
  # print("3 :",new_sentence  )
  encoded = tokenizer.texts_to_sequences([new_sentence])
  # print("4 :",encoded  )
  pad_new = pad_sequences(encoded, maxlen = max_len)
  # print(pad_new)
  score = float(loaded_model.predict(pad_new))
  # print(score)
  if(score > 0.5):
    print("{:.2f}% 확률로 긍정 리뷰입니다.".format(score * 100))
  else:
    print("{:.2f}% 확률로 부정 리뷰입니다.".format((1 - score) * 100))


sentiment_predict1('진짜 배송도 늦고 개짜증나네요. 뭐 이런 걸 상품이라고 만듬?')
sentiment_predict1('판매자님... 너무 짱이에요.. 대박나삼')
sentiment_predict1('ㅁㄴㅇㄻㄴㅇㄻㄴㅇ리뷰쓰기도 귀찮아')
sentiment_predict1('판매자님... 너무 짱이에요.. 대박나삼')









