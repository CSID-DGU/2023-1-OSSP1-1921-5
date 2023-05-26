import React from 'react';
import PulseLoader from 'react-spinners/PulseLoader';

const LoadingSpinner = ({op}) => {
    return (
        <div>
            { op ?
                <div
                    style={{
                        position: "absolute",
                        top: "50%",
                        left: "50%",
                        transform: "translate(-50%, -50%)",
                    }}
                >
                    <PulseLoader color="#007FFF" size={12} margin={4} />
                </div> :
                <div
                    style={{
                        position: 'relative',
                        margin: "20px 0 20px 50%",
                        left: -25,
                        width: 50,
                        textAlign: 'center',
                    }}
                >
                    <PulseLoader color="silver" size={8} margin={3} />
                </div>
            }
        </div>
    );
}

export default LoadingSpinner;