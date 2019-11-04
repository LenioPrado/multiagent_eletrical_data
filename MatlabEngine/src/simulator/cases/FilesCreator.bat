@echo off
cd /d E:\Develop\multiagent_eletrical_data\MatlabEngine\src\simulator\cases\group3\
FOR /L %%V IN (1,1,20) DO copy "BaseSimulationGroup3.java" SimulatorGroup3Case%%V.java