#!/bin/bash

JAR_NAME='XMLParser-1.0-SNAPSHOT-jar-with-dependencies.jar'

ARGUMENTS="file:///home/slaskawiec/work/PRZEMEK/src/git/XMLParser/Package/xml/projekty_offline_mod_fixed.xml"\
" --output-directory ./output"\
" --output-csv-name index.csv"\
" --constant-img-main-uri-template ./images/wizualizacje/%s"\
" --constant-img-location-uri-template ./images/usytuowanie/%s"\
" --constant-img-projection-uri-template ./images/rzuty/%s"\
" --constant-img-elevation-uri-template ./images/elewacje/%s"\
" --output-img-width 640"\
" --output-img-height 480"\
" --constant-amount 99"\
" --constant-active 1"\
" --constant-img-link-template <img\u0020src=\"%s\"\u0020alt=\"\"\u0020/>"\
" --output-img-compression-ratio 0.9"

echo "PROGRAM ARGUMENTS: $ARGUMENTS"

java -jar $JAR_NAME $ARGUMENTS
    
