#!/bin/bash
#
# ImageConv for EasyLEED
#
# v. 20140918a
#
# Nicola Ferralis <ferralis@mit.edu>
#
# This script is licensed under the GNU Public License v.3


# Define and display basename
basename=140523
savename=140523qc

# User interface
echo
echo Basename = $basename
savename=140523qc
echo Baseame output files = $savename
echo

# Define energy start (first image) and step
evstep=0.25
evstart=12

# Actual image processing
i=0
echo Processing...
while [ -f ${basename}_${i}_0.png ]; do

en=`echo $evstart+$i*$evstep|bc`

convert ${basename}_${i}_0.png ${basename}_${i}_1.png ${basename}_${i}_2.png ${basename}_${i}_3.png ${basename}_${i}_4.png -evaluate-sequence mean ${savename}${en}.png
i=$((i+1))

done

# Final stats
echo
echo Energy start = $evstart
evend=`echo $evstart+$i*$evstep|bc`
echo Energy end = $evend
echo "Processed $i image sets"
echo
