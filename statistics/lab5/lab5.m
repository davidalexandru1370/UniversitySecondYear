# big X denotes a random variable in probabilities
# big X denotes characteristics of a population in statistics
# it has a pdf: f(x,theta) theta - unknowkn parameter also known as target
# parameter
#GOAL: estimate theta
#Make a selection of volum n
# obtain X1...Xn variables
# that are independent & identically distrubuted with X
# (X1...Xn have the samp pdf as X)
#Methods:
#1) use a selection function theta (cu bara deasupra) theta(cu bara deasupra)(X1,...Xn)
#2 find thetaLow thetaHigh (bounds left-right) such that P(thetaLow < theta < thetaHigh) = 1 - alpha
#Terminilogy
#(thetaLow , thetaHigh) - 100(1-alpha)% Confidence Interval for theta
#1 - alpha = confidence level(coeff)
#alpha = significance level (L6)
#Usually 1-alpha belongs [0,1] but we take values 0.95 - 0.99

#u = population mean (niu - litera greceasca) (theoretical mean)
#x = vector of data/sample
#x (cu bara deasupra) = sample mean (mean(x))
#sigma * sigma (simga la patrat) = population variance
#s^2 = sample variance (var(x) to compute sample variance)
#sigma = population standard deviation
#s = sample standard deviation (std(x) in octave)
#n = sample size (length(x))
#quantiles
#Z(alpha) - quantile of order alpha of the N(0,1)
# norm inv
x = [7 7 4 5 9 9 4 12 8 1 8 7 3 13 2 1 17 7 12 5 6 2 1 13 14 10 2 4 9 11 3 5 12 6 10 7];
n = length(x);
x_bar = mean(x);
confidence_level = input("input 1-alpha=");
alpha = 1 - confidence_level;
z1 = norminv(1-alpha/2);
z2 = norminv(alpha/2);

sigma = 5;
m1 = x_bar - sigma/sqrt(n)*z1;
m2 = x_bar - sigma/sqrt(n)*z2;
print("The confidence interval for the population mean when sigma known is %4.3f %4.3f", m1,m2);

