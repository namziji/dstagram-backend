FROM centos:7

# Install basic packages
ENV JAVA_TOOL_OPTIONS -Dfile.encoding=UTF8
ENV LANG kr_KO.UTF-8

RUN yum -y update
RUN yum install -y wget telnet nc tar vim unzip net-tools java-11-openjdk-devel

# Dstagram setup
RUN mkdir -p /dstagram/operation
VOLUME ["/dstagram/operation"]
EXPOSE 80 8080
WORKDIR /dstagram
ADD run.sh /dstagram/run.sh
RUN chmod 755 /dstagram/run.sh
ADD war/*.war /dstagram/

ENTRYPOINT ["/dstagram/run.sh"]