
# a7p1.py

def doubleWord(word):
    actualWord = word
#    print(len(actualWord))
#	
#	Returns True if word consists of some word repeated twice.
#	Returns False otherwise.
#	Examples: 
#		doubleWord("cat") -> False
#		doubleWord("catcat") -> True
#		doubleWord("contour"*2) -> True
#		doubleWord("acatcat") -> False
#		doubleWord("catcatcat") -> False
#		doubleWord("catcatcatcat") -> True
#	
    
    if (len(actualWord)%2==0):
        length = int(len(actualWord))
        halfLength = int(len(actualWord)/2)
        firstHalf = actualWord[0:halfLength]
        secondHalf = actualWord[halfLength:length]
        
        if (firstHalf == secondHalf):
            return True
        else:
            return False
    
    else:
        return False
#
print(doubleWord('dogdogdogdog'))
print(doubleWord('dogdog1'))
print(doubleWord('dogdogdogdog'))


def repeatedWord(word):
    actualWord = word
    chunkSize = repeatedWordRecursion(actualWord, 1)
    if(chunkSize>(len(actualWord)/2)):
        print(1)
    else: 
        print(int((len(actualWord))/chunkSize))
        
def repeatedWordRecursion(actualWord, chunkSize):
    for j in range(0, (len(actualWord)-chunkSize), chunkSize):
        print
        chunk1 = (actualWord[j:chunkSize+j])
        chunk2 = actualWord[chunkSize+j:(chunkSize*2)+j]
        if(chunk1==chunk2):
            if(j==(len(actualWord)-(chunkSize*2))):
                return chunkSize
        else:
            chunkSize=chunkSize+1
            if(chunkSize<=(len(actualWord)/2)):
                return repeatedWordRecursion(actualWord, chunkSize)
            else:
                return chunkSize


repeatedWord('dogdogdogdogdogdog') #6
repeatedWord('dogdogdocdogdogdoc') #should return 2
repeatedWord('dogdogdogdogdogdoc') # should return 1
repeatedWord('dogdogdogdoc') #1
repeatedWord('dogdogdogdog') #4
repeatedWord("WWWWWWW") #7
repeatedWord("cat"*10) #10