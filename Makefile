all: run

clean:
	rm -f out/Solver.jar out/Parcs.jar

out/Solver.jar: out/parcs.jar src/Solver.java src/Dto.java src/Matrix.java
	@javac -cp out/parcs.jar src/Solver.java src/Dto.java src/Matrix.java
	@jar cf out/Solver.jar -C src Solver.class -C src Dto.class -C src Matrix.class
	@rm -f src/Solver.class src/Dto.class src/Matrix.class

out/Parcs.jar: out/parcs.jar src/Parcs.java src/Dto.java src/Matrix.java
	@javac -cp out/parcs.jar src/Parcs.java src/Dto.java src/Matrix.java
	@jar cf out/Parcs.jar -C src Parcs.class -C src Dto.class -C src Matrix.class
	@rm -f src/Parcs.class src/Dto.class src/Matrix.class

build: out/Solver.jar out/Parcs.jar

run: out/Solver.jar out/Parcs.jar
	@cd out && java -cp 'parcs.jar:Solver.jar' Solver
