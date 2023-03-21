#hypothesis testing
#past/previous experiences: H0 - null hypothesis
#future/alternatives: H1 - alternate/research hypothesis

#Usually we have:
# H0: theta=theta0
# H1: either one of the following  theta < theta0 (left-tailed test)
#                                  theata > theta0 (right-tailed test)
#                                  theta != theta0 (two-tailed test test-bilateral pe romana)
#the result of one of this statistical test is one the following:
# - reject H0
# - do not reject H0

#Terminology:
#alpha = significance level belongs (0,1) but in application
# alpha will belong to [0.05, 0.01, 0.001]

#TS = test statistic
#TS0 = TS(theta=theta0) = observed value of the TS
#RR - rejection region
#P value - minimum threshold of rejection(smallest alpha we still reject H0)

#How do we decide if we reject or do not reject H0?
#folosim numai reject/do not reject H0 fara accept sau altceva
#Hypothesis testing if TS0 belongs to RR then reject H0
#                   else do not reject H0

#significance testing
#VERY IMPORTANT:
# print clearly on the screen TS0, RR, P
#exercise 1.a
#what are we interesting finding out, what is our unknown, what needs estimation?
#if you hear average the parameters that needs estimation is mean
#previous experience tells me that i can have a good computer if the average
#number of files stored is >= 9

#H0: u = 9
#H1: u < 9  a computer is not able to met the standard
#this is a left-tailed test for the mean
# when sigma is known

alpha = input("input the signifance level = ")
x = [7 7 4 5 9 9 4 12 8 1 8 7 3 13 2 1 17 7 12 5 6 2 1 13 14 10 2 4 9 11 3 5 12 6 10 7];
n = length(x);
#explanation part
#the null hypothesis is H0: mu = 9
#the alternative hypothesis is H1 < mu < 9
#left-tailed test for the mean, when sigma is known
printf("solving 1a\n");
printf("left-tailed test for mu, when sigma is known");
m0 = 9;
sigma = 5;
#[H,P,CI,Z,ZCRIT] = ztest(x,m0,sigma,'alpha')
[h,p,ci,z,zcrit] = ztest(x,m0,sigma,"alpha",alpha,"tail","left");
#H either 0 or 1
#0 - H0 is not rejected
#1 - H0 is rejected
#P - Pvalue
#Z - TS0
#X - data
#M - m0
#SIGMA - sigma
#NAME belongs to {'alpha', 'tail'}
#VALUE depends on NAME

z2 = norminv(alpha);
RR = [-inf, z2];

printf("The value of h is %d\n",h);
if h == 1
   printf("the null hypothesiss H0 is rejected\n");
   printf("The data suggests that the standard is not met\n");
else
  printf("The null hypothesis H0 is not rejected\n");
  printf("The data suggests that the standard is met\n");
endif

printf("The rejected region is (%4.4f, %4.4f)\n",RR);
printf("The observed value of the test statistic is %4.4f\n",z);
printf("The P-value of the is %4.4f\n",p);

#H0 m0 = 5.5
#H1 u > 5.5
#right-tailed test for m0, sigma unknown
[h,p,ci,STATS] = ttest(x,m0,"alpha",alpha,"tail","right");
#struct STATS.tstat = TS0

t2 = tinv(1-alpha,n-1);
RR = [t2, inf];

printf("The value of h is %d\n",h);
if h == 1
   printf("the null hypothesiss H0 is rejected\n");
   printf("The data suggests that the standard is not met\n");
else
  printf("The null hypothesis H0 is not rejected\n");
  printf("The data suggests that the standard is met\n");
endif

printf("The rejected region is (%4.4f, %4.4f)\n",RR);
printf("The observed value of the test statistic is %4.4f\n",STATS.tstat);
printf("The P-value of the is %4.4f\n",p);
