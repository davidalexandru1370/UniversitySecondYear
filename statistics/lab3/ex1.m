#ex1 in the case of normal law
# a) P(X ≤ 0) and P(X ≥ 0);
#pgk load statistics2

niu = input("niu=");
sigma = input("sigma=");

normcdf(0,niu,sigma);
res = 1 - normcdf(0,niu,sigma);

#b) P(−1 ≤ X ≤ 1) and P(X ≤ −1 or X ≥ 1)
#here i compute this with help of formula F(b)-F(a)
b = normcdf(1,niu,sigma) - normcdf(-1,niu,sigma);

#P(X ≤ −1 or X ≥ 1)
b2 = 1 - b;

#c norminv

alpha = input("alpha=");
norminv(alpha,niu,sigma);

#d  the value xβ such that P(X > xβ) = β, for β ∈ (0, 1) (xβ is the quantile of
#order 1 − β).
#here i compute the inverse on 1-Beta
beta = input("beta=");
norminv(beta,niu,sigma)
#between every subpoint insert comments in which you explained
#what you did
#student law - s
#fischef law - f
