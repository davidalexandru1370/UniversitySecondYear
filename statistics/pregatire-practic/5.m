clc

standard = [46 37 39 48 47 44 35 31 44 37];
new = [35 33 31 35 34 30 27 32 31 31];
alpha = 0.05;
n1 = columns(standard);
n2 = columns(new);

m1=mean(standard);
m2=mean(new);

s1=var(standard);
s2=var(new);

f1=finv(alpha/2,n1-1,n2-2);
f2=finv(1-alpha/2,n1-1,n2-2);

%a) At the 5% significance level, do the population variances seem to differ?
alpha = 0.05;
%H0: sigma1 = sigma2
%H1: sigma1 != sigma2 (two-tailed test)
#We know the normality of the population + independent samples;
#we want to test the equality of 2 variables => use formula 4 (the ratio
#of 2 population variances) from the Tests document
[h,p,ci,stats] = vartest2(standard,new,"alpha",alpha);

if h==0
    fprintf("The null hypothesis is not rejected\n.The variances seem to be equal.\n");
else
   fprintf("The null hypothesis is rejected.\n The variances seem to be different.\n");
end

fprintf("The rejected region RR is (%6.4f, %6.4f) U (%6.4f, %6.4f)\n",-inf,f1,f2,inf);
fprintf("The P-value is = %6.4f\n",p);
fprintf("The observed value for the test statistic is = %6.4f\n",stats.fstat);

%b Find a 95% confidence interval for the difference of the average assembling times
#normal underlying populations; unknown variances but from a) we know
#if they are equal or not
#we use formula 3.

s1=cov(standard);
s2=cov(new);

if h==0
     #when variances seem to be equal 
    sp=sqrt(((n1-1)*s1*s1 + (n2-1)*s2*s2)/(n1+n2-2));
    u1 = m1 - m2 - tinv(1-alpha/2,n1+n2-2)*s1*sp*sqrt(1/n1 + 1/n2);
    u2 = m1 - m2 + tinv(1-alpha/2,n1+n2-2)*s1*sp*sqrt(1/n1 + 1/n2);
    fprintf("The confidence interval for difference of means (standard deviation unknown, equal) is %4.3f %4.3f\n",u1,u2);
else
    #when variances seem to be different
    #formula 3.3 from conf_int document
    sp=sqrt(((n1-1)*s1*s1 + (n2-1)*s2*s2)/(n1+n2-2));
    c = s1/n1/(s1/n2 + s2/n2);
    n = 1/(c*c/(n1-1) + (1-c)*(1-c)/(n2-1));
    u1 = m1 - m2 - tinv(1-alpha/2,n)*s1*sp*sqrt(1/n1 + 1/n2);
    u2 = m1 - m2 + tinv(1-alpha/2,n)*s1*sp*sqrt(1/n1 + 1/n2);

    fprintf("The confidence interval for difference of means (standard deviation unknown, equal) is %4.3f %4.3f\n",u1,u2);

end
  
  