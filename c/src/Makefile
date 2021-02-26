CC = gcc
CFLAGS = -g -Wall

TARGET = faillox

all: $(TARGET)

$(TARGET): main.o
	$(CC) $(CFLAGS) -o $(TARGET) main.c 

main.o: main.c 
	$(CC) -c main.c

clean:
	rm *.o
	rm $(TARGET)