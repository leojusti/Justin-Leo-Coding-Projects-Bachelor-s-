def max_position(a, b, c, d, e, f, g, h, i):
    if (a>b):
        return 0
    elif (b>c):
        return 1
    elif (c>d):
        return 2
    elif (d>e):
        return 3
    elif (e>f):
        return 4
    elif (f>g):
        return 5
    elif (g>h):
        return 6
    elif (h>i):
        return 7
    elif (i>h):
        return 8
    elif (a==b==c==d==e==f==g==h==i):
        return -1
    else:      
        return

print(max_position(2,2,2,77,2,2,2,2,2))
print(max_position(15,2,2,2,2,2,2,2,2)) 
print(max_position(3,3,3,3,3,3,3,3,3))

# there is no problem with finding the max position if negative numbers are also included
