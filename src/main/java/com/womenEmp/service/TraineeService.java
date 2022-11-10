package com.womenEmp.service;

import java.util.List;
import com.womenEmp.dto.TraineeDTO;
import com.womenEmp.exception.WomenEmpException;

public interface TraineeService {
	/**
	 * Add trainee to the database .If trainee already present or aadhar no already then throws Exception
	 * @param traineeDTO
	 * @return TraineeDTO
	 * @throws WomenEmpException
	 */
	public TraineeDTO addTrainee(TraineeDTO traineeDTO) throws WomenEmpException;
	/**
	 * If trainee Id not found then throw exception else update trainee
	 * @param traineeDTO
	 * @return TraineeDTO
	 * @throws WomenEmpException
	 */
	public TraineeDTO updateTrainee(TraineeDTO traineeDTO) throws WomenEmpException;
	/**
	 * Get trainee by Trainee Id
	 * @param traineeId
	 * @return TraineeDTO
	 * @throws WomenEmpException
	 */
	public TraineeDTO viewTrainee(Integer traineeId) throws WomenEmpException;
	/**
	 * If trainees list is empty throw exception else return list
	 * @return List<TraineeDTO>
	 * @throws WomenEmpException
	 */
	public List<TraineeDTO> viewAllTrainee() throws WomenEmpException;
	/**
	 * If trainee id not found throw exception else delete trainee
	 * @param traineeId
	 * @throws WomenEmpException
	 */
	public void deleteTrainee(Integer traineeId) throws WomenEmpException;
	/**
	 * If trainee location not found then throw exception else return list
	 * @param location
	 * @return List<TraineeDTO>
	 * @throws WomenEmpException
	 */
	public List<TraineeDTO> viewAllTraineeByLocation(String location) throws WomenEmpException;
	/**
	 * If aadhar no not found throw exception else return traineeDTO
	 * @param aadharNo
	 * @return TraineeDTO
	 * @throws WomenEmpException
	 */
	public TraineeDTO viewTraineeByAadhar(long aadharNo) throws WomenEmpException;
	
}
