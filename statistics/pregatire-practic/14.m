steel = [4.6 0.7 4.2 1.9 4.8 6.1 4.7 5.5 5.4];
glass = [2.5 1.3 2.0 1.8 2.7 3.2 3.0 3.5 3.4];


#alpha = input("input significance level(0,1)=")
alpha=0.05; #5% signifance level
n1 = columns(steel);
n2 = columns(glass);

ms=mean(steel);
mg=mean(glass);

ss=var(steel);
sg=var(glass);

#a. At the 5% signiface level, do the population variance seem to differ?
#h0: sigma1 = sigma2 i.e sigma_s = sigma_g
#h1: sigma1 != sigma2 i.e sigma_s != sigma_g

#we know the normality of the population + independent samples; we want
#to test the equality of 2 variances -> use formula 4 (the ratio of 2
#population variances) from the Tests document
#sigma_s != sigma_g -> two tailed test
tail = 0; #two tailed test

[h, p, ci, stats] = vartest2(steel,glass,alpha,tail);
#pa = p-value; ci = confidence interval

if h == 0
  fprintf("H0 is not rejected, i.e, sigmas are equal\n");
  fprintf("The variance seem to be equal\n");
else
  fprintf("H0 is rejected, population variances differ\n");
  fprintf("The variance seem to be different");
  end

q1=finv(alpha/2,stats.df1, stats.df2);
q2=finv(a-alpha/2,stats.df2,stats.df1);

fprintf("Observed value is %1.4f \n",stats.fstat);
fprintf("P-value is %1.4f\n", p);
fprintf("Rejection region is (-inf, %3.4f) U (%3.4f, inf)\n",q1,q2);
