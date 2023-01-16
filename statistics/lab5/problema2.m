clc
pkg load statistics
#lab5
premium = [22.4 21.7 24.5 23.4 21.6 23.3 22.4 21.6 24.8 20.0];
regular = [17.7 14.8 19.6 19.6 12.1 14.8 15.4 12.6 14.0 12.2];

#a) Assuming σ1 = σ2, find a 100(1 − α)% confidence interval for the difference of the
#true means.

alpha = input("input alpha=");

meanPremium = mean(premium);
meanRegular = mean(regular);

n1 = columns(premium);
n2 = columns(regular);

covariancePremium = cov(premium); #s1
covarianceRegular = cov(regular); #s2

sp = sqrt(((n1-1)*covariancePremium + (n2-1)*covarianceRegular)/(n1+n2-2));

t1 = tinv(1-alpha/2,n1+n2-2);
t2 = tinv(1-alpha/2, n1+n2-2);

u1 = meanPremium - meanRegular - t1 * sp * sqrt(1/n1 + 1/n2);
u2 = meanPremium - meanRegular + t2 * sp * sqrt(1/n1 + 1/n2);
fprintf("-------a-------\n")
fprintf("The confidence interval for the diffrence of true is %4.3f %4.3f\n",u1,u2);


fprintf("--------b-------\n");

#b) Assuming σ1 != σ2, find a 100(1 − α)% confidence interval for the difference of the
#true means.

c = (covariancePremium /n1 )/(covariancePremium/n1 + covarianceRegular/n2);

n = 1/(c*c/(n1-1) + (1-c)*(1-c)/(n2-1));

t12 = tinv(1-alpha/2,n);

u12 = meanPremium - meanRegular - t12 * sqrt(meanPremium/n1 + meanRegular/n2);
u22 = meanPremium - meanRegular + t12 * sqrt(meanPremium/n1 + meanRegular/n2);

fprintf("The confidence interval when sigma1 != sigma2  is %4.3f %4.3f\n",u12,u22);
fprintf("--------c------\n")
#(c) Find a 100(1 − α)% confidence interval for the ratio of the variances.)

a1 = 1/finv(1-alpha/2, n1-1,n2-1) * meanPremium/meanRegular;
a2 = 1/finv(alpha/2,n1-1,n2-1) * meanPremium/meanRegular;

fprintf("The confidence interval for ratio of the variances is %4.3f %4.3f \n",a1,a2);

