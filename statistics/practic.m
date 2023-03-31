pkg load statistics
clc
steel = [4.6 0.7 4.2 1.9 4.8 6.1 4.7 5.5 5.4];
glass = [2.5 1.3 2.0 1.8 2.7 3.2 3.0 3.5 3.4];

#A study is conducted to compare heat loss in glass pipes, versus 
#steel pipes of the same size. Various liquids at identical starting
# tempereatures are run through segments of each type and the heat loss
#(in C) is measured. These data results(normality of the two 
#two populations is assumed):

#a. At the 1% significance level, do the population variances seem to differ?

#The null hypothesis H0 is V(steel)/V(glass) = 1
#The alternative hypothesis is V(steel)/V(glass) != 1 where V is variance
#two tailed test
#We will use the 4 formula from tests document for ratio of two population
#variances

n1 = columns(steel);
n2 = columns(glass);
alpha = input("input significance level = ");
#we compute the fisher quantiles
f1 = finv(alpha/2, n1-1, n2-1);
f2 = finv(1-alpha/2, n1-1, n2-1); %quantiles for the rejection region of the two tailed test
fprintf("Solving a)\n");
[H, P, CI, ZVAL] = vartest2(steel, glass, "alpha", alpha,"tail","both");
#H  is the rejection of the null hypothesis, P is the P-Value, CI is the confidence interval and ZVAL is the value of the test statistic

fprintf("The value of H is = %4.3f\n",H);
if H==0
    fprintf('The null hypothesis is not rejected.\n')
    fprintf('The variances seem to be equal\n')
    fprintf('The rejection region for F is (%6.4f, %6.4f) U (%6.4f, %6.4f)\n', -inf, f1, f2, inf)
    fprintf('The value of the test statistic F is %6.4f\n', ZVAL.fstat)
    fprintf("The confidence interval is (%4.4f, %4.4f)\n",CI);
    fprintf('The P-value for the variances test is %6.4f\n', P)
else
    fprintf('The null hypothesis is rejected.\n')
    fprintf('The variances seem to be different.\n')
    fprintf('The rejection region for F is (%6.4f,%6.4f)U(%6.4f,%6.4f)\n', -inf, f1, f2, inf)
    fprintf('The value of the test statistic F is %6.4f\n', ZVAL.fstat)
    fprintf("The confidance interval is (%4.4f, %4.4f)\n",CI);
    fprintf('The P-value for the variances test is %6.4f\n', P) 
end

fprintf("Solving b)\n");

#b Find a 99% confidence interval for the difference of the average heat losses
#by the point a) we know the variances seem to be equal

confidence_level = 1 - alpha;
#confidence_level = input("confidence level = ");

#we compute mean of steel
meanSteel = mean(steel);
#we compute mean of glass
meanGlass = mean(glass);

#we compute covariance of glass
covarianceGlass = cov(glass); #s1
#we compute covariance of steel;
covarianceSteel = cov(steel); #s2

sp = sqrt(((n1-1)*covarianceGlass + (n2-1)*covarianceSteel)/(n1+n2-2));

#we compute the student quantile 
t1 = tinv(1-alpha/2,n1+n2-2);
t2 = tinv(1-alpha/2, n1+n2-2);

#we use the formula 3.2 from the conf_int document
u1 = meanSteel - meanGlass - t1 * sp * sqrt(1/n1 + 1/n2);
u2 = meanSteel - meanGlass + t2 * sp * sqrt(1/n1 + 1/n2);

fprintf("-------b-------\n")
fprintf("The confidence interval for the difference of heat losses is (%4.3f %4.3f)\n",u1,u2);