clc
pkg load statistics
x = [3.26, 1.89, 2.42, 2.03, 3.07, 2.95, 1.39, 3.06, 2.46, 3.35, 1.56, 1.79, 1.76, 3.82, 2.42, 2.96];
n = columns(x);
#a. Find a 95% confidence interval for the average size of nickel particles.
#0.95
confidence_level = input("input confidence level = ");
alpha = 1 - confidence_level; # => 0.05
s = std(x);
t1 = tinv(1-alpha/2,n-1);
t2 = tinv(alpha/2,n-1);

x_bar = mean(x);

u1 = x_bar - s/sqrt(n)*t1;
u2 = x_bar - s/sqrt(n)*t2;

fprintf("The confidence interval for the average size of nickel particles is %4.3f %4.3f \n",u1,u2);

#b.At the 1% signifance level, on average, do these nickel particles seem to be smaller than 3?

alpha = input("significance level ="); # 0.01

m0 = 3;
fprintf("We have a left tailed test, so we will use the second part of the first case\n");
fprintf("The null hypothesis is mu = 3\n");
fprintf("The alternative hypothesis is m0 < 3\n");

[H,P,CI,STAT] = ttest(x,m0,"alpha",alpha,"tail","left");
#H is the rejection regions, P is the p-value, CI is the confidence interval and STAT is the value of test statistic
fprintf("The value of H is = %4.3f\n",H);
if H == 0
  fprintf("The null hypothesis H0 is not rejected\n");
  fprintf("The particles seem to be greater than 3\n");
else
  fprintf("The null hypothesis H0 is rejected\n");
  fprintf("The particles seem to be smaller than 3\n");
end

q = tinv(alpha,n-1);

fprintf("The rejection region is (-Inf, %6.4f)\n",q);
fprintf("The confidence interval is (%6.4f, %6.4f)\n",CI);
fprintf("The value of the test statistic is %6.4f.\n",STAT.tstat);
fprintf("The P-value for the variances test is %6.4f\n",P);