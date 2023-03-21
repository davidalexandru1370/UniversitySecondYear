n=input("number of simulation = ");
# nr < 0.5 => head else tail
C = rand(3,n);
C
Y = C < 0.5;
Y
#reduce column to sum of its elements
X = sum(Y);
X
hist(X);

