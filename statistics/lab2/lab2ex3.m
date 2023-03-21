#a
x=0:1:3
y=binopdf(0,3,0.5);
#b
#c
#P(X=0)
y=binopdf(0,3.0,.5);
#P(X!=0)
z=1-binopdf(0,3,0.5);
#d
#P(X<=2)
y2=binocdf(2,3,0.5);
#P(X<2)
#(P<=1) || (P<2)
y3=binocdf(1,3,0.5);
#E
#P(X>=1) = 1 - P(X<1) = 1 - P(X<=0)
y4= 1 - binocdf(0,3,0.5);
#P(X>1) = 1  - P(X<=1)
y5 = 1 - binocdf(1,3,0.5);
#print("The probability of the event (X>1) %f",y5);
