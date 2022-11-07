p=input("p=");
# p belongs [0.05,0.75]
#for structure start : step size : end
#two variables are equal if the pdfs coincide
for n = 1 : 10 : 100
  x=0:n;
  plot(x,binopdf(x,n,p));
  pause(0.5);

  endfor
