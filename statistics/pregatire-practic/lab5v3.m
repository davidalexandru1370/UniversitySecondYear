clc
pkg load statistics

x = [7 7 4 5 9 9 4 12 8 1 8 7 3 13 2 1 17 7 12 5 6 2 1 13 14 10 2 4 9 11 3 5 12 6 10 7];

#a) Assuming that past experience indicates that σ = 5, find a 100(1 − α)% confidence
#interval for the average number of files stored.
n=columns(x);
x_bar = mean(x);
confidence_level = input("input confidence level =");
alpha = 1 - confidence_level;
z1=norminv(1-alpha/2);
z2=norminv(alpha/2);

sigma=5;

m1=x_bar - sigma/sqrt(n)*z1;
m2=x_bar - sigma/sqrt(n)*z2;

fprintf("The confidence interval for the population mean when sigma is known is (%4.3f %4.3f)\n",m1,m2);

fprintf("--------b--------\n")

#b) Assuming nothing is known about σ, find a 100(1 − α)% confidence interval for
#the average number of files stored.

s=std(x);
t1=tinv(1-alpha/2 , n-1);
t2=tinv(alpha/2 , n-1);

x_bar = mean(x);

u1 = x_bar - s/sqrt(n)*t1;
u2 = x_bar - s/sqrt(n)*t2;

fprintf("The confidence interval for the population mean when sigma unknown is (%4.3f, %4.3f)\n", u1,u2);
fprintf("------c------\n");
#c) Assuming the number of files stored are approximately normally distributed, find
#100(1 − α)% confidence intervals for the variance and for the standard deviation

kail1 = chi2inv(1-alpha/2, n-1);
kail2 = chi2inv(alpha/2, n-1);
sample_variance = var(x);

w1 = (n-1)*sample_variance/kail1;
w2 = (n-1)*sample_variance/kail2;

fprintf("The confidence interval for population variance is %4.3f %4.3f\n",w1,w2);

fprintf("The confidence interval for population variance is %4.3f %4.3f\n",sqrt(w1),sqrt(w2));