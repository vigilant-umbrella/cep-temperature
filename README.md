# Big Data Analytics project

## A complex event processing based smart temperature monitoring system using rules and pattern matching mechanism.

### Team Members
| Members            | Roll Number | GitHub            |
|--------------------|-------------|-------------------|
| Nikhil Kumar Gupta | 2019BCS036  | [TheNinza](https://github.com/TheNinza)         |
| Aniket Sharma      | 2019BCS008  | [aniketsharma00411](https://github.com/aniketsharma00411) |
| Ashok Arora        | 2019BCS075  | [ashok-arora](https://github.com/ashok-arora)       |
| Yashpal Parmar     | 2019BCS073  | [zatch07](https://github.com/zatch07)           |

### Project Description
Complex Event Processing (CEP) is a novel and promising methodology that
enables the real-time analysis of stream event data. The main purpose of CEP is detection of
the complex event patterns from the atomic and semantically low-level events such as sensor,
log, or RFID data. Determination of the rule patterns for matching these simple events based
on the temporal, semantic, or spatial correlations is the central task of CEP systems. In the
current design of the CEP systems, experts provide event rule patterns. Having reached
maturity, the Big Data Systems and Internet of Things (IoT) technology require the
implementation of advanced machine learning approaches for automation in the CEP domain.
The goal of this research is proposing a machine learning model to replace the manual
identification of rule patterns. After a pre-processing stage (dealing with missing values, data
outliers, etc.), various rule-based machine learning approaches were applied to detect
complex events. Promising results with high preciseness were obtained. A comparative
analysis of the performance of classifiers is discussed.

### Models:

1. Logistic Regression

```
if 0.0666978372814782 * temp + -2.2967445564281768 > 0 then Out else In
Rule: if temp > 34.435067912853846 then Out else In
Accuracy: 0.5448435546995062
Precision, recall, fscore, support = (0.5383151316992414, 0.5580264835873134, 0.4989024089178367, None)
```

2. Ridge regression

```
if -0.0011124815013233826 * temp + 0.0390194635535728 > 0 then Out else In
Rule: if temp > 35.07425831994163 then Out else In
Accuracy: 0.5031248079011537
Precision, recall, fscore, support = (0.5002213580725341, 0.5003353723959655, 0.4552192597408631, None)
```

3. Support Vector Machine

```
if -0.018570082272149464 * temp + 0.6576746409676695 > 0 then Out else In
Rule: if temp > 35.41581729845209 then Out else In
Accuracy: 0.514271663627236
Precision, recall, fscore, support = (0.5113693193398633, 0.5172252849144252, 0.46744082445285, None)
```