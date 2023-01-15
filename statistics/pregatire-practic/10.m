pkg load statistics
clc

a = [1021 980 1017 988 1005 998 1014 985 995 1004 1015 995 1023];
b = [1070 970 993 1013 1006 1002 1014 997 1002 1010 975];

#a.At the 5% significance level, do the population variances seem to differ?
#The null hypothesis H0 is V(a)/V(b) = 1
#The alternative hypothesis is V(a)/V(b) != 1 where V is variance
#two tailed test
#We will use the 4 formula from tests document for ratio of two population
#variances

n1 = columns(a);
n2 = columns(a);
alpha = input("input significance level = ");
f1 = finv(alpha/2, n1-1, n2-1);
f2 = finv(1-alpha/2, n1-1, n2-1); %quantiles for the rejection region of the two tailed test
fprintf("Solving a)\n")
[H, P, CI, ZVAL] = vartest2(a, b, "alpha", alpha,"tail","both");
#H  is the rejection of the null hypothesis, P is the P-Value, CI is the confidence interval and ZVAL is the value of the test statistic
fprintf("The value of H is = %4.3f\n",H);
if H==0
    fprintf('The null hypothesis is not rejected.\n')
    fprintf('The variances seem to be equal\n')
    fprintf('The rejection region for F is (%6.4f, %6.4f) U (%6.4f, %6.4f)\n', -inf, f1, f2, inf)
    fprintf('The value of the test statistic F is %6.4f\n', ZVAL.fstat)
    fprintf("The confidance interval is (%4.4f, %4.4f)",CI);
    fprintf('The P-value for the variances test is %6.4f\n', P)
else
   fprintf('The null hypothesis is rejected.\n')
    fprintf('The variances seem to be different.\n')
    fprintf('The rejection region for F is (%6.4f,%6.4f)U(%6.4f,%6.4f)\n', -inf, f1, f2, inf)
    fprintf('The value of the test statistic F is %6.4f\n', ZVAL.fstat)
    fprintf("The confidance interval is (%4.4f, %4.4f)",CI);
    fprintf('The P-value for the variances test is %6.4f\n', P) 
end

fprintf("Solving b)\n");

% H0 = mu_a = mu_b -> mu_a - mu_b = 0
% H1 = mu_a > mu_b => mu_a - mu_b > 0
% sigma_a = sigma _ b (unknown) based on a) because variances seems to be equal
fprintf("Right tailed test for the difference of two population means with the smae standard deviation, unknown \n");

[H2,PVAL2,CI2,STATS2] = ttest2(a,b,"alpha",alpha,"tail","right");

if H2 == 1
  fprintf("The null hypothesis H0 is rejected\n");
  fprintf("The data suggests that the first supplier A is more reliable\n");
else
  fprintf("The null hypothesis H0 is not rejected\n");
  fprintf("The data suggests that the first supplier A is not reliable\n");
end

fprintf("The rejection region is (%4.4f, %4.4f)\n",tinv(1-alpha,STATS2.df),inf);
fprintf("The observed value for the test statistic is %4.4f\n",STATS2.tstat);
fprintf("THE P-value for the test is %4.4f\n",PVAL2);
fprintf("The confidence interval is (%4.4f, %4.4f)",CI);
