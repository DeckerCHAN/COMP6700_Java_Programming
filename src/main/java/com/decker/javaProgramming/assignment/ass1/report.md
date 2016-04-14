# COMP 6700 Java Programming Assignment 1 Report

## Features

### (Compulsory) List category

List all category in current folder by using "-l" option.

### (Compulsory) Count words

1. Count word frequency in one file by directly add the file path in command line.

2. Count word frequency in multiple file but in same category by add "-c" option in command line.
Programme will calculate word frequency into a table called **WordFrequencyTable**.
This table will combine together during final phase of procedure

3. Count all categores provided by properties file through add "-a" in command line.


#### Counting words by default design

If there are no additional argument, programme is going to count word frequency in normal.
Split content into word and loop sequence, put words into a **HashMap**.

#### Counting words by *optimised* design

If "-o" has been added into command line. Programme will trying to optimise process speed **or** memory usage.

Programme will read file line by line and use *String.intern* method before add it to **HashMap**.

### (Extra) Properties auto generate

It is available to generate a *ass1.properties* file by searching running folder.

If there is no *ass1.properties* found, this feature will automatically execute.

## Design

As the image shown below. The program design is leading by Operation-oriented.
To be exactly, the structure contains by 3 parts: **Primary Logic Control**, **Entities** and **Operations**.

1. Primary logic control:
Primary logic is going to lead the direction to programme but small details.

2. Entities:
The classed designed to contain data and easier to transfer between different module in programme.

3. Operations:
Operations are going to handle detailed logic. For example, how the files been parsed or how it could be analise.


## Performance

After few execution, I have realised