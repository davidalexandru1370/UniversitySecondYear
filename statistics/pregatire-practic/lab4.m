#a) Bernoulli distribution
#p = input("give the probability=");
#N = input("give the number of simulations=");
#U=rand(1,N)
#X = (U < p)
#U_X = unique(X)
#n_X = hist(X,length(U_X))
#rel_freq= n_X/N

clear
#p = input("give the probability=");
#n = input("give the number of trials=");
#N = input("give the number of simulations=");
#for i=1:N
#    U = rand(n,1)
#    X(i)= sum(U<p);
    
#endfor
#k=0:n;
#U_X=unique(X);
#n_X=hist(X,length(U_X));
#rel_freq=n_X / N;
#plot(U_X,rel_freq,'*',k,binopdf(k,n,p),'*r')

p = input('give the probability=');
N = input('give the number of simulations=');
for i=1:N
  X(i) = 0;
  while rand >= p
      X(i) = X(i)+1;
  endwhile
endfor
k=0:20
U_X=unique(X);
n_X=hist(X,length(U_X));
rel_freq=n_X/N;
plot(U_X,rel_freq,'*',k,geopdf(k,p),'*r')

