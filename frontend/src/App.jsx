import {useEffect, useState} from "react";
import {Button, message, Table} from "antd";
import {deleteCustomer, getAllCustomers} from "./api/customerApi";

const App = () => {
    const [customers, setCustomers] = useState([]);
    const [selectedCustomer, setSelectedCustomer] = useState(null);
    const [, setIsCustomerModalVisible] = useState(false);

    useEffect(() => {
        fetchCustomers();
    }, []);

    const fetchCustomers = async () => {
        const response = await getAllCustomers();
        setCustomers(response?.data);
    }

    const handleDelete = async (id) => {
        await deleteCustomer(id);
        message.success("Customer deleted");
        await fetchCustomers();
    }

    const columns = [
        {title: "ID", dataIndex: "id", key: "id"},
        {title: "Name", dataIndex: "customerName", key: "customerName"},
        {title: "Phone Number", dataIndex: "phoneNumber", key: "phoneNumber"},
    ]
    return (
        <>
            <Button
                onClick={() => {
                  setSelectedCustomer(null);
                  setIsCustomerModalVisible(true);
                }}>
                New Account
            </Button>
          <Button
            onClick={() => setIsCustomerModalVisible(true)}
            disabled={!selectedCustomer}>
            Update
          </Button>
          <Button
            onClick={() => handleDelete(selectedCustomer?.id)}
            disabled={!selectedCustomer}>
            Delete
          </Button>

          <Table
            columns={columns} dataSource={customers}
            rowSelection={{
                type: "radio",
                onChange: (_, selectedRows) => setSelectedCustomer(selectedRows[0])
            }}
            rowKey="id"
          />
        </>
    );
}

export default App;