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

[h, p, ci, stats] = vartest2(steel,glass,"alpha",alpha);
#implicit two-tailed test by documentation

#pa = p-value; ci = confidence interval
f1 = finv(alpha/2,n1-1,n2-1);
f2 = finv(1-alpha/2,n1-1,n2-1);

if h == 0
  fprintf("The null hypothesis H0 is not rejected, i.e, sigmas are equal\n");
  fprintf("The variance seem to be equal\n");
else
  fprintf("The null hypothesis H0 is rejected, population variances differ\n");
  fprintf("The variance seem to be different\n");
end

fprintf('The rejection region RR is (%6.4f , %6.4f) U (%6.4f , %6.4f)\n' ,-inf,f1,f2,inf);
fprintf('The P-value for the variances test is = %6.4f \n', p);
fprintf('The observed value for the test statistic is = %6.4f \n', stats.fstat);

#b. at the same signifcance level, does it seem that on average, steel pipes 
  #lose more heat than glass pipes?

  #h0: miu1 = miu2
  #h1: miu1 > miu2 right-tailed test
  

if h == 0
   n = n1+n2-2;
   t2 = tinv(1-alpha,n);
   [H2,P2,CI2,BSTATS] = ttest2(steel,glass,"alpha","tail","right");
   if H2==1:
     fprintf("The null hypothesis H0 is rejected.\n");
     fprintf("Steel pipe lose more heat than glass pipes")
   else
      fprintf("The null hypothesis H0 is not rejected\n");
      fprintf("Steel pipe DOES NOT lose more heat than glass pipes");
   end
   fprintf("The rejection region for T is (%6.4f,%6.4f)\n",t2,inf);
   fprintf("The observed value of the test statistic is %4.3f",BSTATS.tstat);
   fprintf("The P-value is = %4.3f",p);
else 
    c = (ss/n1)/(ss/n1+sg/n2);
    n = c^2/(n1-1) + (1-c)^2/(n2-1);
    
    n=1/n;
    
    t2=tinv(1-alpha,n); %quantile for right-tailed test (for rejection region)
    [H2,P2,CI2,ZVAL2]=ttest2(steel,glass,"alpha",alpha,"tail","right");
    if H2==1
      fprintf("The null hypothesis H0 is rejected. \n");
      fprintf("Steel pipe lose more heat than glass pipes\n");
    else
      fprintf("The null hypothesis H0 is not rejected. \n");
      fprintf("Steel pipe DOES NOT lose more heat than glass pipes");
    end  
    
   fprintf("The rejection region for T is (%6.4f,%6.4f)\n",t2,inf);
   fprintf("The observed value of the test statistic is %4.3f",BSTATS.tstat);
   fprintf("The P-value is = %4.3f",p);
end




