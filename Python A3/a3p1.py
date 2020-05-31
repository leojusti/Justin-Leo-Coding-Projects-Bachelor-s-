# do nothing

def sum_of_four(a, b, c, d):
    return (a+b+c+d)

def max_value(a, b, c, d):
    if ((a>b) and (a>c) and (a>d) ):
        return a
    if ((b>a) and (b>c) and (b>d)):
        return b
    if ((c>a) and (c>b) and (c>d)):
        return c
    if ((d>a) and (d>b) and (d>c)):
        return d

print (sum_of_four(2,3,0,1.01))
print (max_value(2,3,0,1.01))


print (sum_of_four(-1,2.7,-2,1))
print (max_value(4.3,-333,22,1.234))