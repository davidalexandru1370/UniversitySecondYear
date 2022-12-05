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
