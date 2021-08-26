from flask import Flask, request
from ML.classify import model,BERTClassifier,BERTDataset
app = Flask(__name__)
model = model()

@app.route('/', methods = ['POST'])
def classify():
    sentence = str(request.form['sentence'])
    print(sentence)
    return str(model.predict(sentence)[0])

if __name__ == "__main__" :
    app.run(host='0.0.0.0', port=30000, debug=True)
