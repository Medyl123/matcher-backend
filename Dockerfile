FROM ubuntu:jammy-20240405 as java

ENV JAVA_HOME /usr/local/openjdk-17

ENV PATH $JAVA_HOME/bin:$PATH

RUN apt-get update \

    && apt-get full-upgrade --yes \

    && apt-get install --yes --no-install-recommends \

        binutils \

        bzip2 \

        ca-certificates \

        libdigest-sha-perl \

        unzip \

        wget \

        xz-utils \

    && rm -rf /var/lib/apt/lists/*

RUN wget -O openjdk.tgz https://download.java.net/java/GA/jdk21.0.2/f2283984656d49d69e91c558476027ac/13/GPL/openjdk-21.0.2_linux-x64_bin.tar.gz \

    && echo "a2def047a73941e01a73739f92755f86b895811afb1f91243db214cff5bdac3f *openjdk.tgz" | shasum --strict --check - \

    && mkdir -p "$JAVA_HOME" \

    && tar --extract \

        --file openjdk.tgz \

        --directory "$JAVA_HOME" \

        --strip-components 1 \

        --no-same-owner \

    && rm openjdk.tgz* \

    && javac --version \

    && java --version

###########################

FROM ubuntu:jammy-20240405

ENV JAVA_HOME /usr/local/openjdk-17

ENV PATH $JAVA_HOME/bin:$PATH

COPY --from=java $JAVA_HOME $JAVA_HOME

RUN apt-get update \

    && apt-get full-upgrade --yes \

    && apt-get install --yes --no-install-recommends \

       maven \

    && rm -rf /var/lib/apt/lists/*

RUN mkdir -p /app

COPY . /app

WORKDIR /app

# Build the Spring Boot application

RUN mvn clean package -DskipTests

EXPOSE 8080

ENTRYPOINT [ "java","-jar"]