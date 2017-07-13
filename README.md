Problem Statement
=================
The intent of the problem is to consume input data, apply business rules, then generate output. As it turns out I/O and transformatio$

Requirements
============
Produce a program that consumes the input files and produce outputs according to specification. The requirements are:

1. Takes two program arguments: `pathToDirectory`, `outputFile`
2. The `pathToDirectory` contains either csv or json files, e.g., `input1.csv`, `input2.json`
3. Writes output to the specified `outputFile`

The `outputFile` must be a text file with one JSON object per line. Each JSON object, when pretty-printed, should look like the follo$
```
{
  "collectionId": "input1.csv",
  "sites": [
    {
      "id": "12344",
      "name": "example.com",
      "mobile": 1,
      "keywords": "sports,tennis,recreation",
      "score": 117.49
    },
    {
      "id": "12345",
      "name": "example.jp",
      "mobile": 1,
      "keywords": "japan,travel",
      "score": 38
    }
  ]
}
```
Assume the keywords property is populated by calling `KeywordService.resolveKeywords()`.
