x = [7 7 4 5 9 9 4 12 8 1 8 7 3 13 2 1 17 7 12 5 6 2 1 13 14 10 2 4 9 11 3 5 12 6 10 7];
s = std(x);
n = length(x);
confidence_level = input("1-alpha=");
alpha = 1 - confidence_level;
t1 = tinv(1-alpha/2,n-1);
t2 = tinv(alpha/2,n-1);
x_bar = mean(x);

u1 = x_bar - s/sqrt(n)*t1;
u2 = x_bar - s/sqrt(n)*t2;

printf("The confidence interval for the population mean when sigma unknown is %4.3f, %4.3f\n", u1,u2);

