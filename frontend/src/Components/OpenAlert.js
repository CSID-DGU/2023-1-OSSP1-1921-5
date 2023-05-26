import * as React from 'react';
import Box from '@mui/material/Box';
import Snackbar from '@mui/material/Snackbar';
import MuiAlert from '@mui/material/Alert';

const Alert = React.forwardRef(function Alert(props, ref) {
    return <MuiAlert elevation={6} ref={ref} variant="filled" {...props} />;
  });
  
  export default function OpenAlert() {
    const [open, setOpen] = React.useState(false);
  
    const handleClick = () => {
      setOpen(true);
    };
  
    const handleClose = (event, reason) => {
      if (reason === 'clickaway') {
        return;
      }
  
      setOpen(false);
    };
  
    return (
      <Box>
        <Box>
            <button className="btn" variant="contained" onClick={handleClick}>회원가입</button>
        </Box>
        <Snackbar open={open} autoHideDuration={3000} onClose={handleClose} anchorOrigin={{vertical: 'bottom', horizontal: 'center'}}>
          <Alert onClose={handleClose} severity="success" sx={{ width: '100%' }}>
            회원가입 성공!
          </Alert>
        </Snackbar>
      </Box>
    );
  }