matrix1=[1 0 -2;2 1 3; 0 1 0];
matrix2=[2 1 1;1 0 -1; 1 1 0];
res1 = matrix1-matrix2
res2 = matrix1*matrix2
res3 = matrix1.*matrix2

x = 0:0.1:3;
plot(x,x.^5/10,"-;x^5/10;",x,x.*sin(x),"-r;x*sin(x);",x,cos(x),"-b;cos(x);");


