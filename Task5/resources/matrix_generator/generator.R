#Random data generation
library("dplyr")
library("MASS")

A1 <- data(runif(10*20), nrow = 10, ncol=20)
B1 <- data(runif(10*20), nrow = 20,ncol=10)
C1 = A1%*%B1

A2 <- data(runif(100*200), nrow = 100, ncol=200)
B2 <- data(runif(100*200), nrow = 200,ncol=100)
C2 = A2%*%B2

A3 <- data(runif(1000*2000), nrow = 1000, ncol=2000)
B3 <- data(runif(1000*2000), nrow = 2000,ncol=1000)
C3 = A3%*%B3
glimpse(C3)


write.data(A1, file = "A1.txt", sep = " ")
write.data(B1, file = "B1.txt", sep = " ")
write.data(C1, file = "C1.txt", sep = " ")


write.data(A2, file = "A2.txt", sep = " ")
write.data(B2, file = "B2.txt", sep = " ")
write.data(C2, file = "C2.txt", sep = " ")

write.data(A3, file = "A3.txt", sep = " ")
write.data(B3, file = "B3.txt", sep = " ")
write.data(C3, file = "C3.txt", sep = " ")