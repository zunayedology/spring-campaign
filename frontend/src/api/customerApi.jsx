import axios from "axios";

const API_URL = "http://localhost:8080/api";

const axiosInstance = axios.create({
  baseURL: API_URL,
  timeout: 2000,
  headers: {
    'Content-Type': 'application/json',
  },
});

export const getAllCustomers = () => axiosInstance.get("/customers");
export const getCustomer = (id) => axiosInstance.get(`/customers/${id}`);
export const createCustomer = (customer) => axiosInstance.post("/customers", customer);
export const updateCustomer = (id, customer) => axiosInstance.put(`/customers/${id}`, customer);
export const deleteCustomer = (id) => axiosInstance.delete(`/customers/${id}`);