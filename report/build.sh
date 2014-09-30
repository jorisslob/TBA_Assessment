#!/bin/bash

sam2p Simulator.png Simulator.eps
gnuplot makeplot
rubber -pdf -Wall report.tex
