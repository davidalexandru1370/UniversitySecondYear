#1. Let X have one of the following distributions: X ∈ N (µ, σ) (normal), X ∈ T (n)
#(Student), X ∈ χ2 (n), or X ∈ F (m, n) (Fischer). Compute the following:
#a) P (X ≤ 0) and P (X ≥ 0);

#niu=input("niu=");
#sigma=input("sigma=");

#normcdf(0,niu,sigma);
#result = 1 - normcdf(0,niu,sigma)

#b) P (−1 ≤ X ≤ 1) and P (X ≤ −1 or X ≥ 1);

#result1 = normcdf(1,niu,sigma) - normcdf(-1,niu,sigma)

#result2 = 1 - result1

#c) the value xα such that P (X < xα ) = α, for α ∈ (0, 1) (xα is called the quan-
#tile of order α);

#alpha = input("alpha=");
#norminv(alpha,niu,sigma)
#beta=input("beta=");
#norminv(1-beta,niu,sigma)

#Approximations of the Binomial distribution
#• Normal approximation of the binomial distribution: For moderate values of
#p (0.05 ≤ p ≤ 0.95) and large values of n (n → ∞),

#Bino(n, p) ≈ Norm µ = np, σ =
#q
#np(1 − p) .
#Write a Matlab code to visualize how the binomial distribution gradually takes
#the shape of the normal distribution as n → ∞.

#n=input("n=");
#p=input("p=");
#n > 30
#p <= 0.05
#lambda = n*p;
#x - number of possible success
#x=[0:n];
#plot(x,binopdf(x,n,p),'g')
#hold on;
#plot(x,poisspdf(x,lambda),'r')

p=input("p=");
# p belongs [0.05,0.75]
#for structure start : step size : end
#two variables are equal if the pdfs coincide
for n = 1 : 10 : 100
  x=0:n;
  plot(x,binopdf(x,n,p));
  pause(0.5);

  endfor


