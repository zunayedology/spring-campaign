import { useEffect, useState } from "react";
import { Button, message, Table } from "antd";
import { deleteCustomer, getAllCustomers } from "./api/customerApi";
import CustomerModal from "./components/CustomerModal";

const App = () => {
  const [customers, setCustomers] = useState([]);
  const [selectedCustomer, setSelectedCustomer] = useState(null);
  const [isCustomerModalVisible, setIsCustomerModalVisible] = useState(false);

  useEffect(() => {
    fetchCustomers();
  }, []);

  const fetchCustomers = async () => {
    const response = await getAllCustomers();
    setCustomers(response?.data);
  };

  const handleDelete = async (id) => {
    await deleteCustomer(id);
    message.success("Customer deleted");
    await fetchCustomers();
  };

  const columns = [
    { title: "ID", dataIndex: "id", key: "id" },
    { title: "Name", dataIndex: "customerName", key: "customerName" },
    { title: "Phone Number", dataIndex: "phoneNumber", key: "phoneNumber" },
  ];
  return (
    <>
      <Button
        onClick={() => {
          setSelectedCustomer(null);
          setIsCustomerModalVisible(true);
        }}
      >
        New Customer
      </Button>
      <Button
        onClick={() => setIsCustomerModalVisible(true)}
        disabled={!selectedCustomer}
      >
        Update Customer
      </Button>
      <Button
        onClick={() => handleDelete(selectedCustomer?.id)}
        disabled={!selectedCustomer}
      >
        Delete Customer
      </Button>

      <Table
        columns={columns}
        dataSource={customers}
        rowSelection={{
          type: "radio",
          onChange: (_, selectedRows) => setSelectedCustomer(selectedRows[0]),
        }}
        rowKey="id"
      />
      {isCustomerModalVisible && (
        <CustomerModal
          visible={isCustomerModalVisible}
          onClose={() => setIsCustomerModalVisible(false)}
          onRefresh={fetchCustomers}
          customer={selectedCustomer}
        />
      )}
    </>
  );
};

export default App;
