x=[7 7 4 5 9 9 4 12 8 1 8 7 3 13 2 1 17 7 12 5 6 2 1 13 14 10 2 4 9 11 3 5 12 6 10 7];
#a
n=length(x);
x_bar=mean(x);
confidence_level=input("input 1-alpha=");
alpha=1-confidence_level;
z1=norminv(1-alpha/2);
z2=norminv(alpha/2);

sigma = 5;

m1 = x_bar - sigma/sqrt(n)*z1;
m2 = x_bar - sigma/sqrt(n)*z2;
fprintf("The confidence interval for the population mean when sigma is known is %4.3f %4.3f",m1,m2);