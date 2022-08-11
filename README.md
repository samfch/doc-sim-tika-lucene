# Multi type document similarity
Document similarity of any supported files in Apache Tika and indexed using Apache Lucene. Calculate distance using cosine similarity

Lucene similarity code were modified from [https://github.com/wimpykid26/ArticleSimilarity](https://github.com/wimpykid26/ArticleSimilarity) to use more recent libraries

## how to
- place any [supported Apache Tika files/documents](https://tika.apache.org/1.10/formats.html) in `raw` directory
- run MultiTypeDocumentSimilarity, change document index in `CosineSimilarity.distance(docVector[4], docVector[i])` as you want

text data created under `data` directory, Lucene index created under `index` directory
