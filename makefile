JC = javac
JFLAGS = -g
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	./NodeElements/KeyValPair.java \
	./NodeElements/Node.java \
	./NodeElements/IndexNode.java \
	./NodeElements/DataNode.java \
	BPlusTree.java \
	treesearch.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
	$(RM) ./NodeElements/*.class