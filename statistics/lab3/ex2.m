#little f - pdf
#big F - cdf
n = input("n=");
p = input("p=");
#n >= 30
#p <= 0.05
lambda=n*p;
#x - number of possible success
x=[0:n];
plot(x,binopdf(x,n,p),'g')
#keep all graphs in the same window
hold on;
plot(x,poisspdf(x,lambda),'r')
