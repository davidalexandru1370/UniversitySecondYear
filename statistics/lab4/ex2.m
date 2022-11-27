#ex2a
clear
p = input('give the probability');
#pkg load statistics
N = input('give the nb of sim'); #random numgers that u wish
U = rand(1,N); #random numbers between 0 and N
X = (U < p);
U_X = unique(X)
n_X = hist(X,length(U_X));
rel_freq = n_X / N
