#!/bin/bash

# RUN APPLICATION
java -cp ./:/dstagram/operation/  \
    -Xms1024m -Xmx1024m \
    -jar /dstagram/*.war \
    --server.port=8080 "$@"