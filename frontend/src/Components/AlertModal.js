import { React, useState } from "react";
import { Link } from "react-router-dom";
import Button from "@mui/material/Button";
import Modal from "@mui/material/Modal";
import Box from "@mui/material/Box";
import Stack from "@mui/material/Stack";
import "./FindPW.css";

const AlertModal = ({ msg1, msg2, move1, move2, op1, op2 }) => {
  const [open, setOpen] = useState(true);
  const handleClose = () => setOpen(false);

  return (
    <div>
      <Modal open={open}>
        <Box className="alertmodal">
          <h3>잠시만요!✋🏻</h3>
          <p>{msg1}</p>
          <p>{msg2}</p>
          <Stack>
            <Link
              to={move1}
              style={{ textDecoration: "none", color: "#007fff" }}
            >
              <Button fullWidth>{op1}</Button>
            </Link>
            {move2 === "/input" ? (
              <Button onClick={handleClose}>{op2}</Button>
            ) : (
              <Link
                to={move2}
                style={{
                  textDecoration: "none",
                  color: "#007fff",
                  width: "100%",
                }}
              >
                <Button fullWidth>{op2}</Button>
              </Link>
            )}
          </Stack>
        </Box>
      </Modal>
    </div>
  );
};

export default AlertModal;
