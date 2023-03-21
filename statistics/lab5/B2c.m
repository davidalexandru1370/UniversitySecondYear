x = [7 7 4 5 9 9 4 12 8 1 8 7 3 13 2 1 17 7 12 5 6 2 1 13 14 10 2 4 9 11 3 5 12 6 10 7];
n = length(x);
confidence_level = input("1-alpha=");
sample_variance = var(x);
alpha = 1 - confidence_level;
kail1 = chi2inv(1-alpha/2,n-1);
kail2 = chi2inv(1-alpha/2,n-1);

w1 = (n-1)*sample_variance/kail1;
w2 = (n-1)*sample_variance/kail2;

printf("The confidence interval for the variance  is %4.3f, %4.3f\n",w1,w2);

printf("The variance  and standard deviation is %4.3f, %4.3f\n",sqrt(w1),sqrt(w2));
