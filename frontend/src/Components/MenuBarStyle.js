import { makeStyles } from '@material-ui/styles';

const useStyles = makeStyles(theme => ({
  root: {
    justifyContent : "left"
  },
  drawer : {
    paddingTop : "20px",
    width: "250px",
  },
  item: {
    display: 'flex',
    paddingTop: 0,
    paddingBottom: 0,
  },
  button: {
    color: "white",
    padding: '10px 8px',
    justifyContent: 'flex-start',
    textTransform: 'none',
    letterSpacing: 0,
    width: '100%',
  },
  btnRoot : {
    paddingLeft : "25px",
    justifyContent : "left !important"
  },
  subMenu : {
    paddingLeft : "50px !important",
  },
  sidebarBackground: {
    backgroundColor: "#EA5514"
  }
}));

export default useStyles;